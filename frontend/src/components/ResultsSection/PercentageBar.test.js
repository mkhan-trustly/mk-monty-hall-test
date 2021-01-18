import PercentageBar from './PercentageBar';
import React from 'react';
import { shallow } from 'enzyme';

describe('<PercentageBar />', () => {
  it('Should render nothing when in-data is undefined', () => {
    const wrapper = shallow(<PercentageBar />);
    expect(wrapper.isEmptyRender()).toBe(true);
  });

  it('With empty elements', () => {
    const wrapper = shallow(<PercentageBar elements={[]} />);
    expect(wrapper.isEmptyRender()).toBe(true);
  });

  it('With valid element', () => {
    const elements = [{ percentage: 100, bgColor: '', label: 'Test bar' }];
    const wrapper = shallow(<PercentageBar elements={elements} />);
    expect(wrapper.text()).toEqual('Test bar');
  });
});
