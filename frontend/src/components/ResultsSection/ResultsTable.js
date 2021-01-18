import React from 'react';
import { PLAYING_STRATEGY } from '../../util/constants';
import './ResultsTable.css';

const ResultsTable = (props) => {
  const { nbrOfSimulations, won, lost, selectedStrategy } = props.gameResult;
  return (
    <div className="div-table">
      <div className="div-table-row">
        <div className="div-table-col" align="center">Total iterations</div>
        <div className="div-table-col" align="center">{nbrOfSimulations}</div>
      </div>
      <div className="div-table-row">
        <div className="div-table-col" align="center">Won</div>
        <div className="div-table-col" align="center">{won}</div>
      </div>
      <div className="div-table-row">
        <div className="div-table-col" align="center">Lost</div>
        <div className="div-table-col" align="center">{lost}</div>
      </div>
      <div className="div-table-row">
        <div className="div-table-col" align="center">Player strategy</div>
        <div className="div-table-col" align="center">{PLAYING_STRATEGY[selectedStrategy]}</div>
      </div>
    </div>
  );
}

export default ResultsTable;
