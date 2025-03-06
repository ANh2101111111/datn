export interface Order {
  id: number;
  date: string;
  status: "Processing" | "Completed";
  total: string;
  items: number;
}

export const orders: Order[] = [
  {
    id: 1357,
    date: "March 15, 2021",
    status: "Processing",
    total: "$125.00",
    items: 2,
  },
  {
    id: 2468,
    date: "June 29, 2021",
    status: "Completed",
    total: "$364.00",
    items: 5,
  },
  {
    id: 2366,
    date: "August 02, 2021",
    status: "Completed",
    total: "$280.00",
    items: 3,
  },
  {
    id: 2367,
    date: "March 05, 2021",
    status: "Completed",
    total: "$126.00",
    items: 4,
  },
  {
    id: 2368,
    date: "March 06, 2021",
    status: "Processing",
    total: "$99.99",
    items: 2,
  },
  {
    id: 2369,
    date: "March 07, 2021",
    status: "Completed",
    total: "$150.50",
    items: 3,
  },
  {
    id: 2370,
    date: "March 08, 2021",
    status: "Completed",
    total: "$210.00",
    items: 5,
  },
];
