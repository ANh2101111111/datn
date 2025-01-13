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
          <div key={index} className="flex flex-row items-center  gap-2">
            <span className="text-lg">
              <IconComponent />
            </span>
            <span className="font-lato  text-text-body">{item.label}</span>
          </div>
        );
      })}
    </div>
  );
};

export default HeaderIcons;
