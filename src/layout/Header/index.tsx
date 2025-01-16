import React from "react";
import { DATA, SEARCH_DATA } from "./components/data";
import HeaderLogo from "./components/HeaderLogo";
import HeaderIcons from "./components/HeaderIcon";
import HeaderInput from "./components/HeaderInput";

const Header: React.FC = () => {
  return (
    <header className="flex flex-col md:flex-row items-center justify-between h-[97px] border-b border-b-gray-200 ">
      {/* Logo */}
      <div className="flex-none w-full md:w-auto flex justify-center md:justify-start mr-6 ">
        <HeaderLogo
          logoSrc={DATA.logoSrc}
          alt={DATA.alt}
          width={DATA.logoWidth}
          height={DATA.logoHeight}
        />
      </div>

      {/* Input Search */}
      <div className="flex-grow mr-[90px]">
        <HeaderInput
          categories={SEARCH_DATA.categories}
          icons={SEARCH_DATA.icons}
        />
      </div>

      {/* Icons */}
      <div className="flex-none w-full md:w-auto flex justify-center">
        <HeaderIcons icons={DATA.icons} />
      </div>
    </header>
  );
};

export default Header;
