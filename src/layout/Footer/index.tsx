import React from "react";
import BoxFooterInfo from "./components/BoxFooter";
import BoxLogo from "./components/BoxLogo";
import { DATA } from "./components/data";
import BoxPayment from "./components/BoxPayment";
import BoxCopyRight from "./components/BoxCopyRight";

const Footer: React.FC = () => {
  return (
    <footer className="w-full">
      <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-6 gap-8">
        <div className="col-span-2 md:col-span-1">
          <BoxLogo />
        </div>

        {DATA.map((section, index) => (
          <div key={index} className="col-span-1">
            <BoxFooterInfo title={section.title} texts={section.texts} />
          </div>
        ))}

        <div className="col-span-2 md:col-span-1 w-10">
          <BoxPayment />
        </div>
      </div>

      <div className="mt-8">
        <BoxCopyRight />
      </div>
    </footer>
  );
};

export default Footer;
