import React, { FC, SVGProps } from "react";

interface Icon {
  icon: FC<SVGProps<SVGSVGElement>>;
  label: string;
}

interface IconsProps {
  icons: Icon[];
}

const HeaderIcons: React.FC<IconsProps> = ({ icons }) => {
  return (
    <div className="flex items-center gap-6">
      {icons.map((item, index) => {
        const IconComponent = item.icon;
        return (
          <div key={index} className="flex items-center gap-2 group">
            <span className="text-lg">
              <IconComponent />
            </span>
            {/* Label hiển thị trên PC */}
            <span className="hidden lg:inline font-lato text-gray-700 group-hover:text-green-500 transition-all duration-300">
              {item.label}
            </span>
          </div>
        );
      })}
    </div>
  );
};

export default HeaderIcons;
