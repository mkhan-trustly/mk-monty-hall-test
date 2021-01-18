import React from 'react';
import { PLAYING_STRATEGY } from '../../util/constants';
import './ResultsTable.css';
import { getCountAsText } from '../../util/Helper';

const ResultsTable = (props) => {
  const { nbrOfSimulations, won, lost, selectedStrategy } = props.gameResult;
  return (
    <div className="div-table">
      <div className="div-table-row">
        <div className="div-table-col" align="center">Total iterations</div>
        <div className="div-table-col" align="center">{`${nbrOfSimulations} iterations`}</div>
      </div>
      <div className="div-table-row">
        <div className="div-table-col" align="center">Won</div>
        <div className="div-table-col" align="center">{getCountAsText(won)}</div>
      </div>
      <div className="div-table-row">
        <div className="div-table-col" align="center">Lost</div>
        <div className="div-table-col" align="center">{getCountAsText(lost)}</div>
      </div>
      <div className="div-table-row">
        <div className="div-table-col" align="center">Player strategy</div>
        <div className="div-table-col" align="center">{PLAYING_STRATEGY[selectedStrategy]}</div>
      </div>
    </div>
  );
}

export default ResultsTable;
