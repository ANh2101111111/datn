"use client";
import { useGetUserInfo } from "@/hooks/useGetUserInfo";
import { Route } from "@/types/route";
import Link from "next/link";
import React, { useMemo } from "react";
import { DATA, HEADER_MOBILE_DATA, SEARCH_DATA } from "./components/data";
import HeaderIcons from "./components/HeaderIcon";
import HeaderInput from "./components/HeaderInput";
import HeaderLogo from "./components/HeaderLogo";
import useWindowSize from "./hooks/useWindowSize";
import { useAuth } from "@/app/context";

const Header: React.FC = () => {
  const { isLogged } = useAuth();
  const { width } = useWindowSize();
  const isMobile = width <= 768;
  const { data } = useGetUserInfo();

  const listNavBar = useMemo(() => {
    return DATA.icons
      .map((icon) => {
        const cond1 = !isLogged && icon.isPrivate;
        const cond2 = isLogged && icon.label === "Login";
        if (cond1 || cond2) {
          return null;
        }

        if (icon.label === "Account") {
          return {
            ...icon,
            label: data?.username || "Account",
          };
        }
        return icon;
      })
      .filter((item) => item);
  }, [data?.username, isLogged]);
  console.log(data, "1221", listNavBar);

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
          <HeaderIcons icons={listNavBar} />
        </div>
      ) : (
        <div className="flex items-center space-x-4">
          {listNavBar
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
