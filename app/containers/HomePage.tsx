import * as React from 'react';
import { useEffect, useState } from 'react';
import { mefarchimArray } from '../utils/loadFile';
import { loadXML } from '../utils/loadXML';
import Menu from '../components/Menu';

const styles = require('./HomePage.css');

const HomePage = function() {

  const [activeMefarchim,setActiveMefarchim] = useState([1,2]);
  const [activeNid,setActiveNid] = useState(1);

  useEffect(() => {
    loadXML(`${activeNid.toString()}.xml`, 'content', activeMefarchim);
  }, [activeNid,activeMefarchim]);

  const onLoadFile = nid => {
    setActiveNid(nid);
  };

  const onChangeCheckbox = (index) => (e)=>{
    let clone = activeMefarchim.slice();
    let found = clone.findIndex(i => i===index);
    if(found >=0){
      clone.splice(found,1)
    }else{
      clone.push(index)
    }
    setActiveMefarchim(clone)
  };


  return (
    <div dir="rtl" className={styles.windowContainer}>
      <nav>
        <div id='menuContainer' className={styles.menuContainer}>
          <Menu onLoadFile={onLoadFile}/>
        </div>

        <div id='middlePanel' className={styles.middlePanel}>
          <div id="indexChapitre" className={styles.indexChapitre}></div>
          <div id="comments" className={styles.comments}>
            {mefarchimArray.map((m, index) => <span key={index} style={{ display: 'block' }}>
              <input type="checkbox" checked={activeMefarchim.indexOf(index) >= 0} onChange={onChangeCheckbox(index)}/> {m} {index}
            </span>)}
          </div>
        </div>

      </nav>
      <main id="content" className={styles.content}>
      </main>
    </div>
  );
};

export default HomePage;
