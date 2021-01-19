import React from 'react';
import './PercentageBar.css';
import { isEmpty } from '../../util/Helper';

const PercentageBar = (props) => {
  const { elements } = props;

  if (isEmpty(elements)) {
    return null;
  }

  const totalPercent = elements.reduce((e1, e2) => e1.percentage + e2.percentage);
  if (totalPercent < 100 || totalPercent > 100) {
    console.warn('Total percent cannot be < or > 100!');
  }
  return (
    <div className="horizontal-bar-container">
      {elements.map((element, index) => {
        const { percentage, bgColor, label } = element;
        return (
          <div key={index} className="horizontal-bar-filler"
               style={{ width: `${percentage}%`, backgroundColor: bgColor }}>
            <span className="horizontal-bar-label">
              {label}
            </span>
          </div>
        );
      })}
    </div>
  );
}

export default PercentageBar;
