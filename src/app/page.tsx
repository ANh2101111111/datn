import IconAccount from "@/layout/assets/icons/IconAccount";
import PopularProducts from "@/layout/PopularProducts";
import Button from "@/uis/common/button";
import React from "react";

export default function Home() {
  return (
    <div className="flex flex-col flex-col-2  ">
      <h1 className="text-2xl font-semibold mb-4">Test Button Component</h1>

      <div className="mb-4">
        <Button variant="primary" size="large">
          Primary Button
        </Button>
      </div>

      <div className="mb-4">
        <Button variant="secondary" size="medium">
          Secondary Button
        </Button>
      </div>

      <div className="mb-4">
        <Button variant="outline" size="small">
          Outline Button
        </Button>
      </div>

      <div className="mb-4">
        <Button variant="rounded" size="large">
          Rounded Button
        </Button>
      </div>

      {/* Button với icon */}
      <div className="mb-4">
        <Button prefixIcon={<IconAccount />} suffixIcon={<IconAccount />}>
          Button with Icons
        </Button>
      </div>

      <div>
        <PopularProducts/>
      </div>
    </div>
  );
}
