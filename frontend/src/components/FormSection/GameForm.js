import React, { useState } from 'react';
import IterationsList from './IterationsList';
import axios from 'axios';
import PlayingStrategy from './PlayingStrategy';
import { getAsQueryParams } from '../../util/Helper';
import './GameForm.css';

const GameForm = (props) => {
  const { setGameResult } = props;
  const [disabled, setDisabled] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    setDisabled(true);
    axios.get(`api/v1/game/simulate?${getAsQueryParams(event)}`)
      .then(res => {
        if (res.status === 200) {
          setGameResult(res.data);
        } else {
          console.error('Could not fetch game result');
        }
      })
      .catch(res => {
        setGameResult({ errorMsg: res });
        console.error(res);
      })
      .finally(() => {
        setDisabled(false);
      })
  }

  return (
    <form onSubmit={handleSubmit}>
      <IterationsList />
      <PlayingStrategy />
      <div>
        <button className="simulate-button" disabled={disabled}>Simulate</button>
      </div>
    </form>
  );
}

export default GameForm;
