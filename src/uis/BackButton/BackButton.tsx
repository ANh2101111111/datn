"use client"
import { useRouter } from "next/navigation"; 
import { FC } from "react";
import Button from "../common/button";

const BackButton: FC = () => {
  const router = useRouter(); 

  return (
    <Button variant="primary"
      onClick={() => router.back()} 
      className="flex items-center gap-2 text-gray-700  hover:text-black transition-all duration-300 mt-"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        strokeWidth={2}
        stroke="currentColor"
        className="w-6 h-6"
      >
        <path strokeLinecap="round" strokeLinejoin="round" d="M15 19l-7-7 7-7" />
      </svg>
      <span className="font-medium">Back</span>
    </Button>
  );
};

export default BackButton;
