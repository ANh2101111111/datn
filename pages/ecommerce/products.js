/* eslint-disable @next/next/no-img-element */
import * as React from "react";
import axios from "@/lib/axios";
import {
  Box, Typography, Button, Grid, Modal, Fade, Backdrop, TextField, FormControl,
  InputLabel, Select, MenuItem, Card, Paper, Table, TableBody, TableCell, TableContainer,
  TableFooter, TableHead, TablePagination, TableRow, IconButton, Tooltip
} from "@mui/material";
import { useTheme } from "@mui/material/styles";
import FirstPageIcon from "@mui/icons-material/FirstPage";
import KeyboardArrowLeft from "@mui/icons-material/KeyboardArrowLeft";
import KeyboardArrowRight from "@mui/icons-material/KeyboardArrowRight";
import LastPageIcon from "@mui/icons-material/LastPage";
import DeleteIcon from "@mui/icons-material/Delete";
import DriveFileRenameOutlineIcon from "@mui/icons-material/DriveFileRenameOutline";
import VisibilityIcon from "@mui/icons-material/Visibility";
import AddIcon from "@mui/icons-material/Add";
import ClearIcon from "@mui/icons-material/Clear";
import Link from "next/link";
import styles from "@/styles/PageTitle.module.css";

const modalStyle = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  height: "100%",
  maxWidth: "700px",
  width: "100%",
  overflow: "auto",
  bgcolor: "background.paper",
  boxShadow: 24,
  borderRadius: "8px",
};

function ProductTablePaginationActions(props) {
  const theme = useTheme();
  const { count, page, rowsPerPage, onPageChange } = props;
  return (
    <Box sx={{ flexShrink: 0, ml: 2.5 }}>
      <IconButton onClick={(e) => onPageChange(e, 0)} disabled={page === 0}>
        {theme.direction === "rtl" ? <LastPageIcon /> : <FirstPageIcon />}
      </IconButton>
      <IconButton onClick={(e) => onPageChange(e, page - 1)} disabled={page === 0}>
        {theme.direction === "rtl" ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
      </IconButton>
      <IconButton
        onClick={(e) => onPageChange(e, page + 1)}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
      >
        {theme.direction === "rtl" ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
      </IconButton>
      <IconButton
        onClick={(e) => onPageChange(e, Math.max(0, Math.ceil(count / rowsPerPage) - 1))}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
      >
        {theme.direction === "rtl" ? <FirstPageIcon /> : <LastPageIcon />}
      </IconButton>
    </Box>
  );
}

export default function Products() {
  const [products, setProducts] = React.useState([]);
  const [categories, setCategories] = React.useState([]);
  const [loading, setLoading] = React.useState(true);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const [open, setOpen] = React.useState(false);
  const [categorySelect, setCategorySelect] = React.useState("");
  const [typeSelect, setTypeSelect] = React.useState("");
  const [editingProduct, setEditingProduct] = React.useState(null);

  const handleOpen = () => setOpen(true);
  const handleClose = () => {
    setOpen(false);
    setEditingProduct(null);
    setCategorySelect("");
    setTypeSelect("");
  };

  const fetchProductsAndCategories = async () => {
    try {
      const [productRes, categoryRes] = await Promise.all([
        axios.get(`${process.env.NEXT_PUBLIC_API_URL}/api/admin/products`),
        axios.get(`${process.env.NEXT_PUBLIC_API_URL}/user/categories/all`),
      ]);
      const sortedProducts = productRes.data.sort((a, b) => b.bicycleId - a.bicycleId);
      setProducts(sortedProducts);
      setCategories(categoryRes.data);
    } catch (error) {
      console.error("Lỗi khi fetch dữ liệu:", error);
    } finally {
      setLoading(false);
    }
  };

  React.useEffect(() => {
    fetchProductsAndCategories();
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const productData = {
      name: data.get("productName"),
      description: data.get("description"),
      image: data.get("imageUrl"),
      rating: 5.0,
      type: typeSelect,
      originalPrice: parseFloat(data.get("price")),
      quantity: parseInt(data.get("quantity"), 10),
      categoryId: parseInt(categorySelect, 10),
    };

    try {
      if (editingProduct) {
        await axios.put(`${process.env.NEXT_PUBLIC_API_URL}/api/admin/products/${editingProduct.bicycleId}`, productData);
      } else {
        await axios.post(`${process.env.NEXT_PUBLIC_API_URL}/api/admin/products`, productData);
      }
      handleClose();
      fetchProductsAndCategories();
    } catch (error) {
      console.error("Lỗi khi submit sản phẩm:", error);
    }
  };

  const handleDelete = async (id) => {
    if (!confirm("Bạn có chắc chắn muốn xoá sản phẩm này không?")) return;
    try {
      await axios.delete(`${process.env.NEXT_PUBLIC_API_URL}/api/admin/products/${id}`);
      fetchProductsAndCategories();
    } catch (error) {
      console.error("Lỗi khi xoá sản phẩm:", error);
    }
  };

  const handleEdit = (product) => {
    setEditingProduct(product);
    setCategorySelect(product.categoryId);
    setTypeSelect(product.type);
    setOpen(true);
  };

  const emptyRows = page > 0 ? Math.max(0, (1 + page) * rowsPerPage - products.length) : 0;

  return (
    <>
      <div className={styles.pageTitle}>
        <h1>Products</h1>
        <ul>
          <li><Link href="/">Dashboard</Link></li>
          <li>Products</li>
        </ul>
      </div>

      <Card sx={{ p: "25px 25px 10px", mb: "15px" }}>
        <Box sx={{ display: "flex", justifyContent: "space-between", pb: "10px" }}>
          <Typography variant="h6">Products</Typography>
          <Button onClick={handleOpen} variant="contained" startIcon={<AddIcon />}>
            Create Product
          </Button>
        </Box>

        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 850 }} aria-label="custom pagination table">
            <TableHead>
              <TableRow>
                <TableCell>Product Name</TableCell>
                <TableCell>Category</TableCell>
                <TableCell align="center">Price</TableCell>
                <TableCell align="center">Description</TableCell>
                <TableCell align="center">Type</TableCell>
                <TableCell align="center">Quantity</TableCell>
                <TableCell align="right">Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {(rowsPerPage > 0
                ? products.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                : products
              ).map((row) => (
                <TableRow key={row.bicycleId}>
                  <TableCell>
                    <Box sx={{ display: "flex", alignItems: "center" }}>
                      <img src={row.image} alt="Product" width={50} style={{ borderRadius: 10 }} />
                      <Typography sx={{ ml: 2 }}>{row.name}</Typography>
                    </Box>
                  </TableCell>
                  <TableCell>{row.category?.name}</TableCell>
                  <TableCell align="center">${row.originalPrice}</TableCell>
                  <TableCell align="center">{row.description}</TableCell>
                  <TableCell align="center">{row.type}</TableCell>
                  <TableCell align="center">{row.quantity}</TableCell>
                  <TableCell align="right">
                    <Tooltip title="View"><IconButton color="info"><VisibilityIcon /></IconButton></Tooltip>
                    <Tooltip title="Edit"><IconButton color="primary" onClick={() => handleEdit(row)}><DriveFileRenameOutlineIcon /></IconButton></Tooltip>
                    <Tooltip title="Delete"><IconButton color="error" onClick={() => handleDelete(row.bicycleId)}><DeleteIcon /></IconButton></Tooltip>
                  </TableCell>
                </TableRow>
              ))}
              {emptyRows > 0 && <TableRow style={{ height: 53 * emptyRows }}><TableCell colSpan={8} /></TableRow>}
            </TableBody>
            <TableFooter>
              <TableRow>
                <TablePagination
                  rowsPerPageOptions={[5, 10, 25, { label: "All", value: -1 }]}
                  count={products.length}
                  rowsPerPage={rowsPerPage}
                  page={page}
                  onPageChange={(e, newPage) => setPage(newPage)}
                  onRowsPerPageChange={(e) => {
                    setRowsPerPage(parseInt(e.target.value, 10));
                    setPage(0);
                  }}
                  ActionsComponent={ProductTablePaginationActions}
                />
              </TableRow>
            </TableFooter>
          </Table>
        </TableContainer>
      </Card>

      <Modal open={open} onClose={handleClose} closeAfterTransition BackdropComponent={Backdrop} BackdropProps={{ timeout: 500 }}>
        <Fade in={open}>
          <Box sx={modalStyle}>
            <Box sx={{ display: "flex", justifyContent: "space-between", p: 2, bgcolor: "#EDEFF5" }}>
              <Typography variant="h6">{editingProduct ? "Edit" : "Create"} Product</Typography>
              <IconButton onClick={handleClose}><ClearIcon /></IconButton>
            </Box>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ p: 3 }}>
              <Grid container spacing={2}>
                <Grid item xs={12}><TextField name="productName" label="Product Name" fullWidth required defaultValue={editingProduct?.name || ""} /></Grid>
                <Grid item xs={12}>
                  <FormControl fullWidth required>
                    <InputLabel>Category</InputLabel>
                    <Select value={categorySelect} onChange={(e) => setCategorySelect(e.target.value)}>
                      {categories.map((cat) => (
                        <MenuItem key={cat.categoryId} value={cat.categoryId}>{cat.name}</MenuItem>
                      ))}
                    </Select>
                  </FormControl>
                </Grid>
                <Grid item xs={12}><TextField name="price" label="Price" type="number" fullWidth required defaultValue={editingProduct?.originalPrice || ""} /></Grid>
                <Grid item xs={12}><TextField name="quantity" label="Quantity" type="number" fullWidth required defaultValue={editingProduct?.quantity || 0} /></Grid>
                <Grid item xs={12}><TextField label="Type" fullWidth value={typeSelect} onChange={(e) => setTypeSelect(e.target.value)} /></Grid>
                <Grid item xs={12}><TextField name="description" label="Description" fullWidth multiline rows={4} defaultValue={editingProduct?.description || ""} /></Grid>
                <Grid item xs={12}><TextField name="imageUrl" label="Image URL" fullWidth defaultValue={editingProduct?.image || ""} /></Grid>
              </Grid>
              <Box sx={{ mt: 3, textAlign: "right" }}>
                <Button variant="contained" type="submit">{editingProduct ? "Update" : "Create"}</Button>
              </Box>
            </Box>
          </Box>
        </Fade>
      </Modal>
    </>
  );
}