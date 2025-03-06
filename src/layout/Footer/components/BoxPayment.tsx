import React from "react";
import Image from "next/image"; // Sử dụng Image từ next/image

import { APP_PAYMENT_DATA } from "./data";

const BoxPayment: React.FC = () => {
  return (
    <div className="col-span-2 md:col-span-4 flex flex-col gap-3 w-[270.43px] h-[255px]">
      <h3 className="text-heading-4 font-quicksand text-text-footer  font-semibold">
        {APP_PAYMENT_DATA.title}
      </h3>

      <p className="text-sm text-gray-600">{APP_PAYMENT_DATA.description}</p>

      <div className="flex  justify-between gap-2">
        {APP_PAYMENT_DATA.apps.map((app, index) => (
          <Image
            key={index}
            src={app.image}
            alt={app.alt}
            width={128}
            height={38}
            className="object-contain"
          />
        ))}
      </div>

      <h4 className="text-text-medium font-lato font-normal text-text-footer">
        Secured Payment Gateways
      </h4>

      <div className="flex  justify-between gap-4">
        {APP_PAYMENT_DATA.paymentGateways.map((gateway, index) => (
          <Image
            key={index}
            src={gateway.image}
            alt={gateway.alt}
            width={224}
            height={32}
            className="object-contain"
          />
        ))}
      </div>
    </div>
  );
};

export default BoxPayment;
