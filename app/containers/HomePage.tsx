import { useState, useEffect, Props } from 'react';
import { loadFile, loadMenu } from '../utils/loadFile';
import * as React from 'react';


interface NODE {
  name?: string;
  nid?: string;
  noc?: string;
  node?: NODE[];
}


const recursiveNode = (node: NODE, onLoadFile, toggleNode, openedNode = {}) => {
  const handleToggle = nodeClicked => () => {
    //console.warn(nodeClicked)
    toggleNode(nodeClicked.name);
    if (nodeClicked && nodeClicked.nid && nodeClicked.nid.toString().length) {
      onLoadFile(nodeClicked.nid.toString());
    }
  };

  if (!node) {
    node = {};
  }

  if (!node.node) {
    node.node = [];
  }

  return (
    <li key={node.name + node.nid}>
      <i
        style={
          {
            fontStyle: 'normal',
            color: node.nid ? 'yellow' : 'white',
            cursor: 'pointer',
            fontSize: 20
          }}
        onClick={handleToggle(node)}>{node && node.name}</i>
      {!!node && !!node.node && !!node.node.length && (
        <ul hidden={!openedNode[node.name.toString()]}>
          {node.node.map(subnode =>
            recursiveNode(subnode, onLoadFile, toggleNode, openedNode)
          )}
        </ul>
      )}
    </li>
  );
};


const Menu = ({ onLoadFile }) => {
  const [menu, setMenu] = useState(null);
  const [openedNode, setOpenedNode] = useState({ 'ובלכתך בדרך': true });

  useEffect(() => {
    if (!menu) {
      loadMenu().then(res => {
        setMenu(res);
      });
    }
  }, []);

  const toggleNode = (nodeName: string) => {
    const openedNodeClone = JSON.parse(JSON.stringify(openedNode));
    openedNodeClone[nodeName.toString()] = !openedNodeClone[
      nodeName.toString()
      ];
    setOpenedNode(openedNodeClone);
  };

  return (menu && recursiveNode(menu, onLoadFile, toggleNode, openedNode));
};


const HomePage = function(props: Props<any>) {
  const loadXML = file =>
    loadFile(file).then((docFragment: DocumentFragment) => {
      const content = document.getElementById('content');
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
        console.error('no div content');
      }
    }, error => {
      console.error(file, error);
    });

  useEffect(() => {
    loadXML('1.xml');
  }, []);

  const onLoadFile = nid => {
    loadXML(`${nid.toString()}.xml`);
  };

  return (
    <div dir="rtl" style={{ display: 'flex', flexDirection: 'row', height: '100%' }}>
      <nav style={{ display: 'flex', flexDirection: 'row', height: '100%', flexBasis: '50%', minWidth: 600 }}>
        <div style={{
          height: '100%',
          minHeight: '100%',
          width: '50%',
          overflow: 'scroll',
          background: '#002a52',
          paddingLeft: 5,
          paddingRight: 5
        }}>
          <Menu onLoadFile={onLoadFile}/>
        </div>

        <div id="indexChapitre"
             style={{
               fontSize: 20,
               height: '100%',
               width: '50%',
               overflow: 'scroll',
               background: '#004169',
               paddingLeft: 5,
               paddingRight: 5,
             }}>
        </div>
      </nav>
      <main
        id="content"
        style={{
          height: '100%',
          overflow: 'scroll',
          textAlign: 'justify',
          paddingLeft: 20,
          paddingRight: 20,
        }}
      >
      </main>
    </div>
  );
};

export default HomePage;
