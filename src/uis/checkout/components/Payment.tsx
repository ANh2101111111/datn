import { PaymentMethod } from "./data";
import Button from "@/uis/common/button";

interface PaymentProps {
  methods: PaymentMethod[];
}

const Payment: React.FC<PaymentProps> = ({ methods }) => {
  return (
    <div className="mt-[20px]">
      <h3 className="font-bold text-heading-4 mb-4">Payment</h3>
      {methods.map((method) => (
        <div key={method.id} className="flex items-center space-x-2 py-1">
          <input type="radio" id={method.id} name="payment" value={method.id} />
          <label htmlFor={method.id} className="cursor-pointer">
            {method.name}
          </label>
        </div>
      ))}
      <Button variant="primary" className=" p-2 mt-4 w-full rounded">
        Place on Order{" "}
      </Button>
    </div>
  );
};

export default Payment;
