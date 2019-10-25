import { loadFile } from './loadFile';

export const loadXML = (fileName:string, divContentId:string, activeMefarchim:number[], activeChapter:string = null) =>
  loadFile(fileName,activeMefarchim,activeChapter).then(({doc,pid}) => {
    const content = document.getElementById(divContentId);
    if (content) {
      content.innerHTML = '';
      content.appendChild(doc);
      const listChapitres = content.querySelectorAll('a[id]');
      const chapters = Array.prototype.map.call(listChapitres, (node: any) => node.id && node.id.trim());

      if(activeChapter){
        return {pid};
      }else{
        return {pid,chapters};
      }

    } else {
      console.error('no divContentId '+ divContentId);
      return {pid:null,chapters:null};
    }
  }, error => {
    console.error(fileName, error);
    return {pid:null,chapters:null};
  });

