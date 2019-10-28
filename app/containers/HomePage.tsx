import * as React from 'react';
import { useEffect, useState } from 'react';
import { loadXML } from '../utils/loadXML';
import Menu from '../components/Menu';
import ListComments from '../components/ListComments';
import ListChapters from '../components/ListChapters';

const styles = require('./HomePage.css');

const HomePage = function() {

  const [activePid, setActivePid] = useState([1]);
  const [activeChapter, setActiveChapter] = useState(null);
  const [activeNid, setActiveNid] = useState(1);
  const [availablePid, setAvailablePid] = useState([]);
  const [availableChapters, setAvailableChapters] = useState([]);

  useEffect(() => {
    loadXML('content', activeNid, activePid, activeChapter)
      .then(({ pid, chapters }) => {
        pid && setAvailablePid(pid);
        chapters && setAvailableChapters(chapters);
      });
  }, [activeNid, activePid, activeChapter]);

  const onLoadFile = nid => {
    setAvailableChapters([]);
    setActiveChapter(null);
    setActiveNid(nid);
  };


  return (
    <div dir="rtl" className={styles.windowContainer}>
      <nav>
        <div id='menuContainer' className={styles.menuContainer}>
          <Menu onLoadFile={onLoadFile}/>
        </div>

        <div id='middlePanel' className={styles.middlePanel}>
          <div id="indexChapitre" className={styles.indexChapitre}>
            <ListChapters activeChapter={activeChapter}
                          availableChapters={availableChapters}
                          onLoadChapter={(chapter => setActiveChapter(chapter))}/>
          </div>
          <div id="comments" className={styles.comments}>
            <ListComments activePid={activePid}
                          availablePid={availablePid}
                          onChangeActivePid={pid => setActivePid(pid)}/>

            {/*<Link to={routes.COUNTER}>*/}
            {/*  <i className="fa fa-code fa-3x" />*/}
            {/*</Link>*/}
          </div>
        </div>

      </nav>
      <main id="content" className={styles.content}/>

    </div>
  );
};

export default HomePage;
