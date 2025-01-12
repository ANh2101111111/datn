import React from "react";
import BoxFooterInfo from "./components/BoxFooter";
import BoxLogo from "./components/BoxLogo";
import { DATA } from "./components/data";
import BoxPayment from "./components/BoxPayment";
import BoxCopyRight from "./components/BoxCopyRight";
const Footer: React.FC = () => {
  return (
    <footer className=" ">
      <div className="grid md:grid-cols-20 grid-cols-2">
        <BoxLogo />
        {DATA.map((section, index) => (
          <BoxFooterInfo
            key={index}
            title={section.title}
            texts={section.texts}
          />
        ))}
        <BoxPayment />
      </div>
      <div>
        <BoxCopyRight />
      </div>
    </footer>
  );
};

export default Footer;
