import React from "react";

export default function BoxCopyRight() {
  return (
    <div className=" flex justify-between font-lato pt-6 text-heading-6 border-t border-b-[#D8F1E5]">
      <p>
        &copy; 2021, <span className="text-[#3BB77E]">NestMart</span> - HTML
        Ecommerce Template
      </p>
      <p>
        Designed by{" "}
        <a href="#" className="hover:text-green-600">
          {" "}
          <span className="text-[#3BB77E]">Alithemes.com </span>
        </a>
        . All rights reserved
      </p>
    </div>
  );
}
