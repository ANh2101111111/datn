import BoxBanner from "@/layout/banner/components/Boxbanner";
import { bannerData } from "@/layout/banner/components/data";
import React from "react";

export default function systemDesign() {
  return (
    <BoxBanner
      title={bannerData.title}
      description={bannerData.description}
      image={bannerData.image}
      icon={bannerData.icon}
    />
  );
}
