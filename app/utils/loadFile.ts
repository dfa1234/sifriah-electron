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


export const mefarchim = new Map<string,string>();
mefarchim.set("1", "אונקלוס");
mefarchim.set("2", "ת' יהונתן");
mefarchim.set("3", "רשי תורה");
mefarchim.set("28", "שפתי חכמים");
mefarchim.set("4", "רמב'ן");
mefarchim.set("5", "אבן עזרא");
mefarchim.set("6", "ספורנו");
mefarchim.set("7", "בעל טורים");
mefarchim.set("8", "אור החיים");
mefarchim.set("29", "כלי יקר");
mefarchim.set("10", "מ' דוד");
mefarchim.set("11", "מ' ציון");
mefarchim.set("12", "רלב'ג");
mefarchim.set("30", "מלבי'ם ביאור תוכן");
mefarchim.set("31", "מלבי'ם ביאור המילות");
mefarchim.set("9", "תורה תמימה");
mefarchim.set("14", "ר' עובדיה מברטנורה");
mefarchim.set("15", "תוספות יום טוב");
mefarchim.set("17", "רש'י");
mefarchim.set("18", "תוספות");
mefarchim.set("20", "משנה ברורה");
mefarchim.set("21", "ביאור הלכה");
mefarchim.set("23", "זוהר מתורגם");

const mefarchimObject = {
"1": "אונקלוס",
"2": "ת' יהונתן",
"3": "רשי תורה",
"28": "שפתי חכמים",
"4": "רמב'ן",
"5": "אבן עזרא",
"6": "ספורנו",
"7": "בעל טורים",
"8": "אור החיים",
"29": "כלי יקר",
"10": "מ' דוד",
"11": "מ' ציון",
"12": "רלב'ג",
"30": "מלבי'ם ביאור תוכן",
"31": "מלבי'ם ביאור המילות",
"9": "תורה תמימה",
"14": "ר' עובדיה מברטנורה",
"15": "תוספות יום טוב",
"17": "רש'י",
"18": "תוספות",
"20": "משנה ברורה",
"21": "ביאור הלכה",
"23": "זוהר מתורגם",
}


export const mefarchimArray = Object.assign([],mefarchimObject);


const getParser = () =>
  new Promise<XSLTProcessor>((resolve, reject) => {
    const filePathXSL = path.join(ROOT, '..', 'xsl', 'displaybook1.xsl');
    fs.readFile(filePathXSL, 'utf8', (err, data) => {
      if (err) reject(err);
      const xsl = new DOMParser().parseFromString(data, 'application/xml');
      const xsltProcessor = new XSLTProcessor();
      xsltProcessor.clearParameters();
      xsltProcessor.setParameter(null,'listPids','1 2');
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
