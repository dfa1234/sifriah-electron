import { useEffect, useState } from 'react';
import { loadMenu } from '../utils/loadFile';
import RecursiveNode from './RecursiveNode';


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

  return (menu && RecursiveNode(menu, onLoadFile, toggleNode, openedNode));
};


export default Menu;
