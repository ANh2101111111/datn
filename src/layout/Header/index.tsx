"use client";
import React from "react";
import { DATA, SEARCH_DATA, HEADER_MOBILE_DATA } from "./components/data";
import HeaderLogo from "./components/HeaderLogo";
import HeaderInput from "./components/HeaderInput";
import useWindowSize from "./hooks/useWindowSize";
import HeaderIcons from "./components/HeaderIcon";
import Link from "next/link";
import { Route } from "@/types/route";

const Header: React.FC = () => {
  const { width } = useWindowSize();
  const isMobile = width <= 768;

  return (
    <header className="flex items-center justify-between px-4 py-2 border-b border-gray-200 bg-white">
      {/* Hamburger Menu chỉ hiển thị trên màn hình nhỏ */}
      {isMobile &&
        HEADER_MOBILE_DATA.map((data, index) => {
          const IconHamburger = data.icon;
          return (
            <button key={index} className="text-2xl text-gray-700">
              <IconHamburger />
            </button>
          );
        })}

      {/* Logo */}
      <div className="flex items-center  mr-6">
        <Link href={Route.HOME}>
          <HeaderLogo
            logoSrc={DATA.logoSrc}
            alt={DATA.alt}
            width={DATA.logoWidth}
            height={DATA.logoHeight}
          />
        </Link>
      </div>

      {!isMobile ? (
        <div className="flex-grow flex items-center justify-between">
          {/* Thanh Tìm Kiếm */}
          <div className="">
            <HeaderInput
              categories={SEARCH_DATA.categories}
              icons={SEARCH_DATA.icons}
            />
          </div>
          {/* Các Icon */}
          <HeaderIcons icons={DATA.icons} />
        </div>
      ) : (
        <div className="flex items-center space-x-4">
          {DATA.icons
            .filter((icon) => ["Wishlist", "Cart"].includes(icon.label))
            .map((icon, index) => {
              const IconHamburger = icon.icon;
              return (
                <div key={index} className="text-2xl text-gray-700">
                  <IconHamburger />
                </div>
              );
            })}
        </div>
      )}
    </header>
  );
};

export default Header;
