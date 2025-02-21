import React from "react";

const listLableData = [
  { id: 1, name: "tất cả" },
  { id: 2, name: "xe dap 1" },
  { id: 3, name: "xe đạp 2" },
  { id: 4, name: "xe dap 3" },
  { id: 5, name: "xe dap 4" },
  { id: 6, name: "xe dap 5" },
  { id: 7, name: "xe dap 6" },
];

interface LableListProps {
  selectedIds?: number[];
}

const BoxListLable: React.FC<LableListProps> = ({ selectedIds }) => {
  const filteredLable = selectedIds
    ? listLableData.filter((lable) => selectedIds.includes(lable.id))
    : listLableData;

  return (
    <div className=" hidden lg:block   items-center  justify-between  p-4">
      <ul className="flex space-x-6">
        {filteredLable.map((lable) => (
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
