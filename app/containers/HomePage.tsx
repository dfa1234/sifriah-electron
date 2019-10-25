import * as React from 'react';
import { useEffect } from 'react';
import { mefarchimArray } from '../utils/loadFile';
import { loadXML } from '../utils/loadXML';
import Menu from '../components/Menu';

const styles = require('./HomePage.css');

const HomePage = function() {

  useEffect(() => {
    loadXML('1.xml', 'content');
  }, []);

  const onLoadFile = nid => {
    loadXML(`${nid.toString()}.xml`, 'content');
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
            {mefarchimArray.map((m, index) => <span style={{ display: 'block' }}>
              <input type="checkbox"/> {m} {index}
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
