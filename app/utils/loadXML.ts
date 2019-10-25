import { loadFile } from './loadFile';

export const loadXML = (fileName:string, divContentId:string, activeMefarchim:number[]) =>
  loadFile(fileName,activeMefarchim).then((docFragment: DocumentFragment) => {
    const content = document.getElementById(divContentId);
    if (content) {
      content.innerHTML = '';
      content.appendChild(docFragment);
      let listChapitres = content.querySelectorAll('a[id]');
      const indexChapitre = document.getElementById('indexChapitre');
      indexChapitre.innerHTML = '';

      Array.prototype.map.call(listChapitres, (node: any) => {
        node.id = node.id && node.id.trim();
      });

      let listNode = Array.prototype.map.call(listChapitres, (node: Node) => {
        let n: any = node.cloneNode(true);
        n.href = '#' + n.id;
        n.id = null;
        delete n.id;
        return n;
      });

      indexChapitre.append(...listNode);
    } else {
      console.error('no divContentId '+ divContentId);
    }
  }, error => {
    console.error(fileName, error);
  });

