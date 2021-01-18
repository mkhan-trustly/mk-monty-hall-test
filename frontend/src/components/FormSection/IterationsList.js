import React from 'react';

const IterationsList = () => (
  <div>
    <label>Run simulation&nbsp;&nbsp;</label>
    <span className="drop-down-list">
        <select id="iterations" name="iterations" className="form-element">
          <option value="10">10 times</option>
          <option value="100">100 times</option>
          <option value="100">1000 times</option>
          <option value="1000">1000 times</option>
          <option value="10000">10000 times</option>
          <option value="100000">100000 times</option>
        </select>
      </span>
  </div>
);

export default IterationsList;
