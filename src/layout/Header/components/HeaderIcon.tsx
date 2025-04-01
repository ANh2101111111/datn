import React from "react";
import Link from "next/link";
import IconLogout from "@/layout/assets/icons/IconLogout";
import { useGetUserInfo } from "@/hooks/useGetUserInfo";
import { Route } from "@/types/route";
import { useRouter } from "next/navigation";
import { useAuth } from "@/app/context";
import { useGetCarts } from "@/api/cart";

interface Icon {
  icon: React.FC<React.SVGProps<SVGSVGElement>>;
  label: string;
  path: string;
}

interface IconsProps {
  icons: Icon[];
}

const HeaderIcons: React.FC<IconsProps> = ({ icons }) => {
  const router = useRouter();
  const { data } = useGetUserInfo();
  const { logout, userId } = useAuth();

  const { data: cart } = useGetCarts({
    variables: Number(userId),
    enabled: !!userId,
    refetchOnMount: true,
  });

  return (
    <div className="flex items-center gap-4 md:gap-6">
      {icons.map((item, index) => {
        const IconComponent = item.icon;
        return (
          <Link
            key={index}
            href={item.path}
            className="flex items-center gap-2 group cursor-pointer"
          >
            <p className="text-base md:text-lg relative">
              {item.label === "Cart" &&
                cart?.cartDetails &&
                cart.cartDetails.length > 0 && (
                  <span className="absolute text-[12px] bg-red-500 px-1.5 min-w-[25px] -top-3 h-6 flex items-center justify-center -right-3 rounded-full text-white font-bold">
                    {cart.cartDetails.length}
                  </span>
                )}

              <IconComponent />
            </p>
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
