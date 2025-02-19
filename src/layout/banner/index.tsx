import React from 'react';
import BoxBanner from './components/Boxbanner';
import { bannerData } from './components/data';

const Banner: React.FC = () => {
  return (
    <BoxBanner
      title={bannerData.title}
      description={bannerData.description}
      image={bannerData.image}
      icon={bannerData.icon}
    />
  );
};

export default Banner;
