const fs = require('fs');
const path = require('path');
const xml2js = require('xml2js');
const { app } = require('electron').remote;

let ROOT = __dirname;

if (
  process.env.NODE_ENV === 'development' ||
  process.env.DEBUG_PROD === 'true'
) {
  ROOT = path.join(__dirname, 'app');
} else {
  ROOT = app.getAppPath();
}

const getParser = () =>
  new Promise<XSLTProcessor>((resolve, reject) => {
    const filePathXSL = path.join(ROOT, '..', 'xsl', 'displaybook1.xsl');
    fs.readFile(filePathXSL, 'utf8', (err, data) => {
      if (err) reject(err);
      const xsl = new DOMParser().parseFromString(data, 'application/xml');
      const xsltProcessor = new XSLTProcessor();
      xsltProcessor.clearParameters();
      xsltProcessor.setParameter('xsl','listPids',['1','2']);
      xsltProcessor.importStylesheet(xsl);
      resolve(xsltProcessor);
    });
  });

export const loadFile = file => {
  return new Promise((resolve, reject) => {
    const filePath = path.join(ROOT, '..', 'archives', file);
    fs.readFile(filePath, 'utf8', (err, data2) => {
      data2 = data2
        .replace('<?xml version="1.0" encoding="utf-8"?>', '')
        .replace('<?xml version="1.0" ?>', '')
        .replace('<?xml version="1.0"?>', '');
      const xml = new DOMParser().parseFromString(data2, 'application/xml');
      getParser().then(xsltProcessor => {
        const resultDocument = xsltProcessor.transformToFragment(xml, document);
        if(resultDocument.textContent.includes('This page contains the following errors')){
          reject(resultDocument.textContent)
        }else{
          resolve(resultDocument);
        }
      });
    });
  });
};

export const loadMenu = () => {
  return new Promise((resolve, reject) => {
    const filePathTanach = path.join(ROOT, '..', 'xsl', 'tanach.xml');
    fs.readFile(filePathTanach, 'utf8', (err, data) => {
      if (err) reject(err);
      xml2js.parseString(data, { mergeAttrs: true }, function(err, result) {
        if (err) reject(err);
        else resolve(result.index);
      });
    });
  });
};
