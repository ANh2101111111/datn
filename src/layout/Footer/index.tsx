import React from "react";
import BoxFooterInfo from "./components/BoxFooter";
import { DATA } from "./components/data";

const Footer: React.FC = () => {
  return (
    <footer className="flex flex-col md:flex-row md:justify-between gap-4 p-4">
      {DATA.map((section, index) => (
        <BoxFooterInfo key={index} title={section.title} texts={section.texts} />
      ))}
    </footer>
  );
};

export default Footer;
