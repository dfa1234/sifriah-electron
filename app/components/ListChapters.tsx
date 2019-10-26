import * as React from 'react';

const styles = require('./ListChapters.css');

const ListChapters = ({ availableChapters, activeChapter, onLoadChapter }) => {

  const onClickChapter = (chapter: string) => (event) => {
    onLoadChapter(chapter);
  };

  // {/*href={'#'+chapter}*/})

  return <>
    {availableChapters && availableChapters.map(chapter =>
      <a onClick={onClickChapter(chapter)}
         key={chapter}
         style={{
           backgroundColor: activeChapter === chapter ? '#505154' : 'transparent'
         }}
         className={styles.linkChapter}
      >
        {chapter}
      </a>)}
  </>;

};


export default ListChapters;
