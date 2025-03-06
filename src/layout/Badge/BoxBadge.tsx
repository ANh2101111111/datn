import { FC } from "react";
import { twMerge } from "tailwind-merge";

interface IBoxBadgeProps {
  text: string;
  className?: string;
}
const BoxBadge: FC<IBoxBadgeProps> = ({ text, className }) => {
  return (
    <div
      className={twMerge(
        `bg-text-brand1 py-2.5 px-4 rounded-tr-[30px] rounded-br-[30px] text-xs leading-3 text-white absolute left-0 top-5`,
        className
      )}
    >
      {text}
    </div>
  );
};

export default BoxBadge;
