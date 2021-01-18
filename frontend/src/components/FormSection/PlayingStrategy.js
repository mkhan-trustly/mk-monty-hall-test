import React from 'react';
import { PLAYING_STRATEGY } from '../../util/constants';

const PlayingStrategy = () => (
  <div>
    <label>Playing strategy</label>
    <span className="drop-down-list">
        <select id="playingStrategy" name="playingStrategy" className="form-element">
          {
            Object.keys(PLAYING_STRATEGY).map((key, index) => (
              <option key={index} value={key}>
                {PLAYING_STRATEGY[key]}
              </option>)
            )}
        </select>
      </span>
  </div>
);

export default PlayingStrategy;
