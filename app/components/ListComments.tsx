import * as React from 'react';

/*
const mefarchim = new Map<string, string>();
mefarchim.set('1', 'אונקלוס');
mefarchim.set('2', 'ת\' יהונתן');
mefarchim.set('3', 'רשי תורה');
mefarchim.set('28', 'שפתי חכמים');
mefarchim.set('4', 'רמב\'ן');
mefarchim.set('5', 'אבן עזרא');
mefarchim.set('6', 'ספורנו');
mefarchim.set('7', 'בעל טורים');
mefarchim.set('8', 'אור החיים');
mefarchim.set('29', 'כלי יקר');
mefarchim.set('10', 'מ\' דוד');
mefarchim.set('11', 'מ\' ציון');
mefarchim.set('12', 'רלב\'ג');
mefarchim.set('30', 'מלבי\'ם ביאור תוכן');
mefarchim.set('31', 'מלבי\'ם ביאור המילות');
mefarchim.set('9', 'תורה תמימה');
mefarchim.set('14', 'ר\' עובדיה מברטנורה');
mefarchim.set('15', 'תוספות יום טוב');
mefarchim.set('17', 'רש\'י');
mefarchim.set('18', 'תוספות');
mefarchim.set('20', 'משנה ברורה');
mefarchim.set('21', 'ביאור הלכה');
mefarchim.set('23', 'זוהר מתורגם');
*/

const mefarchimObject = {
  '1': 'אונקלוס',
  '2': 'ת\' יהונתן',
  '3': 'רש"י תורה',
  '28': 'שפתי חכמים',
  '4': 'רמב"ן',
  '5': 'אבן עזרא',
  '6': 'ספורנו',
  '7': 'בעל טורים',
  '8': 'אור החיים',
  '29': 'כלי יקר',
  '10': 'מ" דוד',
  '11': 'מ\' ציון',
  '12': 'רלב\'ג',
  '30': 'מלבי"ם ביאור תוכן',
  '31': 'מלבי"ם ביאור המילות',
  '9': 'תורה תמימה',
  '14': 'ר\' עובדיה מברטנורה',
  '15': 'תוספות יום טוב',
  '17': 'רש"י',
  '18': 'תוספות',
  '20': 'משנה ברורה',
  '21': 'ביאור הלכה',
  '23': 'זוהר מתורגם'
};


const mefarchimArray = Object.assign([], mefarchimObject);


const ListComments = ({ availablePid, activePid, onChangeActivePid }) => {

  const onChangeCheckbox = (index) => (event) => {
    let clone = activePid.slice();
    let found = clone.findIndex(i => i === index);
    if (found >= 0) {
      clone.splice(found, 1);
    } else {
      clone.push(index);
    }
    onChangeActivePid(clone);
  };

  return (<>{
    mefarchimArray.map((m, index) =>
      availablePid.indexOf(index) > -1 &&
      <span key={index} style={{ display: 'block' }}>
              <input type="checkbox" checked={activePid.indexOf(index) >= 0}
                     onChange={onChangeCheckbox(index)}/> {m}
            </span>)
  }</>);
};

export default ListComments;
