import * as React from 'react';

interface NODE {
  name?: string;
  nid?: string;
  noc?: string;
  node?: NODE[];
}


const RecursiveNode = (node: NODE, onLoadFile, toggleNode, openedNode = {}) => {
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
            RecursiveNode(subnode, onLoadFile, toggleNode, openedNode)
          )}
        </ul>
      )}
    </li>
  );
};


export default RecursiveNode;
