import React from "react";
import { DATA } from "./components/data";
import HeaderLogo from "./components/HeaderLogo";

const Header: React.FC = () => {
  return (
    <header className="flex items-center justify-between p-4 shadow-md bg-white">
      <HeaderLogo
        logoSrc={DATA.logoSrc}
        alt={DATA.alt}
        width={DATA.logoWidth}
        height={DATA.logoHeight}
      />
    </header>
  );
};

export default Header;
