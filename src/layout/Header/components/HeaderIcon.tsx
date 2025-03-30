import React from "react";
import Link from "next/link";
import IconLogout from "@/layout/assets/icons/IconLogout";
import { useGetUserInfo } from "@/hooks/useGetUserInfo";
import { Route } from "@/types/route";
import { useRouter } from "next/navigation";
import { useAuth } from "@/app/context";

interface Icon {
  icon: React.FC<React.SVGProps<SVGSVGElement>>;
  label: string;
}

interface IconsProps {
  icons: Icon[];
}

const HeaderIcons: React.FC<IconsProps> = ({ icons }) => {
  const router = useRouter();
  const { data } = useGetUserInfo();
  const { logout } = useAuth();
  return (
    <div className="flex items-center gap-4 md:gap-6">
      {icons.map((item, index) => {
        const IconComponent = item.icon;
        return (
          <Link
            key={index}
            href={item.label === "Cart" ? "/cart" : "#"}
            className="flex items-center gap-2 group cursor-pointer"
          >
            <span className="text-base md:text-lg">
              <IconComponent />
            </span>
            <span className="text-sm font-lato text-gray-700 group-hover:text-green-500 transition-all duration-300">
              {item.label}
            </span>
          </Link>
        );
      })}

      {data && (
        <div className="flex items-center gap-2 group cursor-pointer">
          <span className="text-base md:text-lg">
            <IconLogout width={12} />
          </span>
          <span
            className="text-sm font-lato text-gray-700 group-hover:text-green-500 transition-all duration-300"
            onClick={() => {
              logout?.();
              router.push(Route.LOGIN);
            }}
          >
            Logout
          </span>
        </div>
      )}
    </div>
  );
};

export default HeaderIcons;