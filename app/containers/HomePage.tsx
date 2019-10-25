import * as React from 'react';
import { useEffect, useState } from 'react';
import { mefarchimArray } from '../utils/loadFile';
import { loadXML } from '../utils/loadXML';
import Menu from '../components/Menu';

const styles = require('./HomePage.css');

const HomePage = function() {

  const [activeMefarchim, setActiveMefarchim] = useState([1]);
  const [activeChapter, setActiveChapter] = useState(null);
  const [activeNid, setActiveNid] = useState(1);
  const [availablePid, setActivePid] = useState([]);
  const [availableChapters, setAvailableChapters] = useState([]);

  useEffect(() => {
    loadXML(`${activeNid.toString()}.xml`, 'content', activeMefarchim, activeChapter)
      .then(({pid,chapters}) => {
        pid && setActivePid(pid);
        chapters && setAvailableChapters(chapters);
      })
  }, [activeNid, activeMefarchim, activeChapter]);

  const onLoadFile = nid => {
    setAvailableChapters([]);
    setActiveChapter(null);
    setActiveNid(nid);
  };

  const onClickChapter = (chapter:string) => (event) => {
    setActiveChapter(chapter);
  };

  const onChangeCheckbox = (index) => (event) => {
    let clone = activeMefarchim.slice();
    let found = clone.findIndex(i => i === index);
    if (found >= 0) {
      clone.splice(found, 1);
    } else {
      clone.push(index);
    }
    setActiveMefarchim(clone);
  };

  return (
    <div dir="rtl" className={styles.windowContainer}>
      <nav>
        <div id='menuContainer' className={styles.menuContainer}>
          <Menu onLoadFile={onLoadFile}/>
        </div>

        <div id='middlePanel' className={styles.middlePanel}>
          <div id="indexChapitre" className={styles.indexChapitre}>
            {availableChapters && availableChapters.map(chapter =>
              <a onClick={onClickChapter(chapter)}
                 key={chapter}
                style={{
                  backgroundColor:activeChapter === chapter ? '#505154':'transparent',
                }}
                 className={styles.linkChapter}
              >
                {chapter}
              </a>)}
            {/*href={'#'+chapter}*/}
          </div>
          <div id="comments" className={styles.comments}>
            {mefarchimArray.map((m, index) =>
              availablePid.indexOf(index) > -1 &&
              <span key={index}
                    style={{ display: 'block' }}>
              <input type="checkbox" checked={activeMefarchim.indexOf(index) >= 0}
                     onChange={onChangeCheckbox(index)}/> {m}
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
