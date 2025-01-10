import React from "react";
import BoxFooterInfo from "./components/BoxFooter";
import BoxLogo from "./components/BoxLogo";
import { DATA } from "./components/data";
import BoxPayment from "./components/BoxPayment";
const Footer: React.FC = () => {
  return (
    <footer
      className="flex justify-center border-b-2 border-b-gray-100"
    > 
      <BoxLogo />
      {DATA.map((section, index) => (
        <BoxFooterInfo
          key={index}
          title={section.title}
          texts={section.texts}
        />
      ))}
      <BoxPayment />
    </footer>
  );
};

export default Footer;
