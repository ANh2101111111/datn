import React, { FC } from "react";
import { LOGO_DATA } from "./data";
import IconLogo from "@/layout/assets/icons/IconLogo";
interface IInformationProps {
  icon: React.ReactNode;
  title: string;
  description: string;
}
const Information: FC<IInformationProps> = ({ icon, title, description }) => {
  return (
    <div className="flex gap-[9.53px] items-start">
      <div>{icon}</div>
      <div>
        <p className="text-text-medium text-text-heading">
          <span className="font-bold">{title}</span> {description}
        </p>
      </div>
    </div>
  );
};
const BoxLogo: FC = ({}) => {
  return (
    <div className="md:col-span-4 col-span-2 w-full h-auto flex flex-col gap-[23.41px]">
      <div>
        <IconLogo />
      </div>
      <div>
        <p className="font-lato text-text-medium text-text-heading">
          Pellentesque posuere orci lobortis
        </p>
      </div>
      <div className="flex flex-col gap-[11.78px]">
        {LOGO_DATA.map((item, index) => {
          return (
            <Information
              key={index}
              title={item.title}
              description={item.description}
              icon={item.icon}
            />
          );
        })}
      </div>
    </div>
  );
};
export default BoxLogo;
