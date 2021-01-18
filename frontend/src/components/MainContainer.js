import GameForm from './FormSection/GameForm';
import PercentageBar from './ResultsSection/PercentageBar';
import React, { useState } from 'react';
import { hasErrors, hasValidResult } from '../util/Helper';
import ResultsTable from './ResultsSection/ResultsTable';
import { ErrorMsg } from './ResultsSection/ErrorMsg';

const MainContainer = () => {

  const [gameResult, setGameResult] = useState({});

  const getGameResult = () => {
    const { winsInPercent, lostInPercent } = gameResult;

    const wins = { percentage: winsInPercent, bgColor: 'blue', label: 'Won ' + winsInPercent + '%' };
    const lost = { percentage: lostInPercent, bgColor: 'red', label: 'Lost ' + lostInPercent + '%'}
    return [wins, lost];
  }

  return (
    <div className="main-container">
      <GameForm setGameResult={setGameResult} />
      {hasErrors(gameResult) && <ErrorMsg gameResult={gameResult} />}
      {hasValidResult(gameResult) && <ResultsTable gameResult={gameResult} />}
      {hasValidResult(gameResult) && <PercentageBar elements={getGameResult()} />}
      {hasValidResult(gameResult) && <p>*Won vs Lost in percentage</p>}
    </div>
  );
}

export default MainContainer;
