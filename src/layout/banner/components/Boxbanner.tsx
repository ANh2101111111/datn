/* eslint-disable @next/next/no-img-element */
import Button from "@/uis/common/button";
import React, { JSX } from "react";

interface IBannerProps {
  title: string;
  description: string;
  image: string;
  icon: JSX.Element;
}

const BoxBanner: React.FC<IBannerProps> = ({
  title,
  description,
  image,
  icon,
}) => {
  return (
    <div className="bg-orange-100 p-6 rounded-xl flex flex-col md:flex-row items-center mb-4 mt-6">
      <div className="flex-1">
        <h2 className=" lg:text-heading-1 md:text-heading-3 sm:text-heading-5  font-bold mb-5 font-quicksand text-text-heading">
          {title}
        </h2>
        <p className="text-heading-4 text-text-body font-lato  mb-3">
          {description}
        </p>

        <div className="mt-4 w-[350px] h-[50px] flex items-center rounded-[30px] bg-brand-thrid p-2 border ">
          <span className="ml-2">{icon}</span>

          <input
            type="email"
            placeholder="Your email address"
            className="ml-2 p-2 mr-4 h-50px max-w-xs rounded-md border-0  text-text-body"
          />

          <Button
            variant="secondary"
            className=" w-[149px] h-[50px] mr-auto text-brand-third px-6  rounded-r-[30px]  rounded-l-[30px] flex items-center gap-2 hover:bg-scale-color2 transition duration-300"
          >
            <span>Subscribe</span>
          </Button>
        </div>
      </div>

      <div className="flex-1">
        <img
          src={image}
          alt="Fruits in a bag"
          className=" rounded-xl bg-transparent md:w-1/2 lg:w-full"
        />
      </div>
    </div>
  );
};

export default BoxBanner;
