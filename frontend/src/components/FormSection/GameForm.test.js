import { shallow } from 'enzyme';
import GameForm from './GameForm';
import React from 'react';
import IterationsList from './IterationsList';
import PlayingStrategy from './PlayingStrategy';

describe('<GameForm />', () => {
  it('Render form', () => {
    const wrapper = shallow(<GameForm />);
    expect(wrapper.find(IterationsList).exists()).toBeTruthy();
    expect(wrapper.find(PlayingStrategy).exists()).toBeTruthy();
  });

  it('Click simulate', () => {
    const wrapper = shallow(<GameForm />);
    wrapper.find('form').simulate('submit', {
      preventDefault: () => {}
    });
    expect(wrapper.find('button').html()).toContain('disabled');
  });
});
