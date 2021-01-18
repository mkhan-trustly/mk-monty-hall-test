import MainContainer from './MainContainer';
import React from 'react';
import { shallow } from 'enzyme';
import GameForm from './FormSection/GameForm';

describe('<MainContainer />', () => {
  it('Render component', () => {
    const wrapper = shallow(<MainContainer />);
    expect(wrapper.find(GameForm).exists()).toBeTruthy();
  });
});
