import React from "react";
import TextColor from "@/uis/system-design/TextColor";


export default function Home() {
  return (
    <div >
      
      <TextColor text="Brand Color 1" className="text-brand-primary font-lato " />
      <TextColor text="Brand Color 2" className="text-brand-secondary font-quicksand text-heading-1 font-medium " />

      <TextColor text="Scale Color 1" className="text-scale-color1 font-medium" />
      <TextColor text="Scale Color 2" className="text-scale-color2" />
      <TextColor text="Scale Color 3" className="text-scale-color3" />
      <TextColor text="Scale Color 4" className="text-scale-color4" />

      <TextColor text="System Success" className="text-system-success" />
      <TextColor text="System Danger" className="text-system-danger" />
      <TextColor text="System Info" className="text-system-info" />
      
      <TextColor text="Heading" className="text-text-heading" />
      <TextColor text="Body" className="text-text-body" />
    </div>
  );
}
