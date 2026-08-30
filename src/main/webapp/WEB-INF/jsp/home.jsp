<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titulo}</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&family=Playfair+Display:ital,wght@0,600;0,700;1,600&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary-navy: #0f172a;
            --accent-gold: #d97706;
            --accent-gold-hover: #b45309;
            --bg-light: #f8fafc;
            --card-border: #e2e8f0;
        }
        body {
            font-family: 'Plus Jakarta Sans', sans-serif;
            background-color: var(--bg-light);
            color: #334155;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .font-serif { font-family: 'Playfair Display', serif; }
        .navbar-custom { background-color: rgba(15, 23, 42, 0.98); backdrop-filter: blur(10px); }
        .btn-gold {
            background-color: var(--accent-gold);
            color: #ffffff;
            font-weight: 600;
            border-radius: 10px;
            padding: 10px 22px;
            border: none;
            transition: all 0.25s ease;
        }
        .btn-gold:hover {
            background-color: var(--accent-gold-hover);
            color: #ffffff;
            transform: translateY(-2px);
        }
        .card-custom {
            background: #ffffff;
            border-radius: 16px;
            border: 1px solid var(--card-border);
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
        }
        .form-control, .form-select {
            border-radius: 10px;
            padding: 10px 14px;
            border: 1.5px solid #cbd5e1;
        }
        .form-control:focus, .form-select:focus {
            border-color: var(--accent-gold);
            box-shadow: 0 0 0 3px rgba(217, 119, 6, 0.15);
        }
    </style>
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark navbar-custom sticky-top py-2">
        <div class="container">
            <a class="navbar-brand d-flex align-items-center gap-2" href="${pageContext.request.contextPath}/home">
                <i class="bi bi-building-fill-check text-warning fs-3"></i>
                <div>
                    <span class="fs-5 fw-bold text-white">GRAND ROYAL</span>
                    <small class="d-block text-warning" style="font-size: 0.65rem; letter-spacing: 2px;">HOTELS & RESORTS (JSP)</small>
                </div>
            </a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mx-auto mb-2 mb-lg-0 fw-medium gap-lg-2">
                    <li class="nav-item"><a class="nav-link active text-warning" href="${pageContext.request.contextPath}/home"><i class="bi bi-house-door me-1"></i>Inicio</a></li>
                    <li class="nav-item"><a class="nav-link text-light" href="${pageContext.request.contextPath}/catalogo"><i class="bi bi-door-open me-1"></i>Habitaciones</a></li>
                </ul>
                <div class="d-flex gap-2">
                    <a href="${pageContext.request.contextPath}/catalogo" class="btn btn-sm btn-outline-light rounded-pill px-3"><i class="bi bi-search me-1"></i>Ver Catálogo</a>
                </div>
            </div>
        </div>
    </nav>

    <!-- Hero Banner -->
    <section style="background: linear-gradient(rgba(15, 23, 42, 0.7), rgba(15, 23, 42, 0.88)), url('https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?auto=format&fit=crop&w=1920&q=80') center/cover; min-height: 460px; display:flex; align-items:center;">
        <div class="container text-white py-5 text-center">
            <span class="badge bg-warning bg-opacity-25 text-warning mb-3 px-3 py-2 rounded-pill fw-bold">
                <i class="bi bi-stars me-1"></i> Implementado en Java JSP / JSTL &bull; DWF 2026
            </span>
            <h1 class="display-4 fw-bold font-serif mb-3">Encuentra Tu Habitación Ideal</h1>
            <p class="lead text-light mb-4 mx-auto" style="max-width: 650px;">
                Consulta la disponibilidad y tarifas procesadas directamente mediante controladores Java y vistas JSP.
            </p>
        </div>
    </section>

    <!-- Formulario Java JSP (Envía parámetros GET a /catalogo) -->
    <div class="container" style="margin-top: -60px; position: relative; z-index: 20;">
        <div class="card-custom p-4 p-md-5 shadow-lg border-0">
            <h4 class="fw-bold mb-4 text-dark"><i class="bi bi-search text-warning me-2"></i>Buscar Disponibilidad de Alojamiento</h4>
            
            <form action="${pageContext.request.contextPath}/catalogo" method="get">
                <div class="row g-3">
                    <div class="col-lg-3 col-md-6">
                        <label class="form-label fw-semibold small text-secondary"><i class="bi bi-geo-alt-fill text-danger me-1"></i>Hotel / Sucursal</label>
                        <select class="form-select" name="sucursalId" required>
                            <c:forEach items="${sucursales}" var="s">
                                <option value="${s.idSucursal}">${s.nombre} (${s.categoriaEstrellas}★)</option>
                            </c:forEach>
                            <c:if test="${empty sucursales}">
                                <option value="1" selected>Hotel Royal San Salvador (5★)</option>
                            </c:if>
                        </select>
                    </div>
                    <div class="col-lg-2 col-md-6">
                        <label class="form-label fw-semibold small text-secondary"><i class="bi bi-calendar-check text-primary me-1"></i>Fecha Entrada</label>
                        <input type="date" class="form-control" name="checkIn" value="${checkInDefault}" required>
                    </div>
                    <div class="col-lg-2 col-md-6">
                        <label class="form-label fw-semibold small text-secondary"><i class="bi bi-calendar-x text-primary me-1"></i>Fecha Salida</label>
                        <input type="date" class="form-control" name="checkOut" value="${checkOutDefault}" required>
                    </div>
                    <div class="col-lg-2 col-md-6">
                        <label class="form-label fw-semibold small text-secondary"><i class="bi bi-people-fill text-success me-1"></i>Huéspedes</label>
                        <select class="form-select" name="huespedes">
                            <option value="1">1 Adulto</option>
                            <option value="2" selected>2 Adultos</option>
                            <option value="3">2 Adultos + 1 Niño</option>
                            <option value="4">4 Huéspedes (Familia)</option>
                        </select>
                    </div>
                    <div class="col-lg-3 col-md-12 d-flex align-items-end">
                        <button type="submit" class="btn btn-gold w-100 py-2 fs-6">
                            <i class="bi bi-search me-2"></i>Buscar Disponibilidad
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white py-4 mt-auto">
        <div class="container text-center small text-secondary">
            <strong>Grand Royal Hotels & Resorts</strong> &bull; JavaServer Pages (JSP) &bull; DWF 2026 &bull; Universidad Don Bosco
        </div>
    </footer>
</body>
</html>
