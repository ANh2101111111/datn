import React from 'react';
import { bannerData } from './components/data';
import BoxBanner from './components/Boxbanner';

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