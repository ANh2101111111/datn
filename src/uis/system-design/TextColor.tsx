import React from "react";
import Button from "../common/button";
import { IconAddress } from "@/layout/assets/icons";
import IconAccount from "@/layout/assets/icons/IconAccount";

export default function TextColor() {
  return (
    <div>
      <Button
        variant="primary"
        size="small"
        prefixIcon={<IconAddress width="10px" />}
      >
        Ha Tinh
      </Button>
      <Button
        variant="secondary"
        size="medium"
        prefixIcon={<IconAccount width="10px" />}
      >
        Ha Tinh
      </Button>
    </div>
  );
}
