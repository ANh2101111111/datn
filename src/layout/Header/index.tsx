import React from "react";
import { DATA } from "./components/data";
import HeaderLogo from "./components/HeaderLogo";
import HeaderIcons from "./components/HeaderIcon";

const Header: React.FC = () => {
  return (
    <header className="flex items-center justify-between bg-white h-[97px]  ">
      <HeaderLogo
        logoSrc={DATA.logoSrc}
        alt={DATA.alt}
        width={DATA.logoWidth}
        height={DATA.logoHeight}
      />
      <HeaderIcons icons={DATA.icons} />
    </header>
  );
};

export default Header;
