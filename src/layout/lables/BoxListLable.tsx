import React from "react";
import { ILabel } from "./type";

interface LableListProps {
  data?: ILabel[];
}

const BoxListLable: React.FC<LableListProps> = ({ data = [] }) => {
  return (
    <div className=" hidden lg:block   items-center  justify-between  p-4">
      <ul className="flex space-x-6">
        {data.map((lable) => (
          <li
            key={lable.id}
            className="text-text-medium text-text-heading font-lato cursor-pointer hover:text-text-brand1  "
          >
            {lable.name}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BoxListLable;
