import React from "react";
import { DATA, SEARCH_DATA } from "./components/data";
import HeaderLogo from "./components/HeaderLogo";
import HeaderIcons from "./components/HeaderIcon";
import HeaderInput from "./components/HeaderInput";
const Header: React.FC = () => {
  return (
    <header className="flex items-center justify-between gap-6 bg-white h-[97px]  ">
      <HeaderLogo
        logoSrc={DATA.logoSrc}
        alt={DATA.alt}
        width={DATA.logoWidth}
        height={DATA.logoHeight}
      />
      <HeaderInput
        categories={SEARCH_DATA.categories}
        icons={SEARCH_DATA.icons}
      />
      <HeaderIcons icons={DATA.icons} />
    </header>
  );
};

export default Header;
