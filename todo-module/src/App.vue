<template>
	<div id="app">

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Id</th>
                <th>Equipo</th>
                <th>Detalle</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="equipo in equipos" :key="equipo.id">
                <td>{{ equipo.id }}</td>
                <td>{{ equipo.nombre }}</td>
            </tr>
        </tbody>
    </table>

    <h4 class="bg-dark text-white text-center p-2">
			{{ name }}'s To Do List
		</h4>

		<div class="container p-4">
			<div class="row" v-if="filteredTasks.length == 0">
				<div class="col text-center font-weight-bold alert alert-success">
					Nothing to do, Hurra!
				</div>
			</div>

			<template v-else>
				<div class="row">
					<div class="col font-weight-bold">Task</div>
					<div class="col-2 font-weight-bold">Done</div>
				</div>

				<div class="row" v-for="t in filteredTasks" v-bind:key="t.action">
					<div class="col">{{ t.action }}</div>
					<div class="col-2 text-center">
						<input type="checkbox"
						v-model="t.done"
						v-on:change="storeData"
						class="form-check-input" />
					</div>
				</div>
			</template>

			<div class="row py-2">
				<div class="col">
					<input type="text" class="form-control" v-model="newItemText" />
				</div>
				<div class="col-2">
					<button class="btn btn-primary" v-on:click="addNewTodo">
						Add
					</button>
				</div>
			</div>

			<div class="row bg-secondary py-2 mt-2 text-white">
				<div class="col text-center">
					<input type="checkbox"
						v-model="hideCompleted"
						class="form-check-input" />
					<label class="form-check-label font-wieight-bold ml-1">
						Hide completed tasks
					</label>
				</div>

				<div class="col text-center">
					<button class="btn btn-sm btn-warning"
						v-on:click="deleteCompleted">
						Delete completed
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
//import equipoApi from '../../services/equipo-api'
import equipoApi from '../src/services/equipo-api'

export default {
  name: 'app',
  data () {
    return {
      name: 'Emmanuel',
      tasks: [],
      hideCompleted: true,
      newItemText: '',
      equipos: [],
      loading: true
    }
  },
  computed: {
    filteredTasks () {
      return this.hideCompleted ? this.tasks.filter(t => !t.done) : this.tasks
    }
  },
  methods: {
    addNewTodo () {
      this.tasks.push({
        action: this.newItemText,
        done: false
      })

      this.storeData()
      this.newItemText = ''
    },

    storeData () {
      localStorage.setItem('todos', JSON.stringify(this.tasks))
    },

    deleteCompleted () {
      this.tasks = this.tasks.filter(t => !t.done)
      this.storeData()
    },

    getEquipos() {
      equipoApi.getAll()
      .then(response => {
          console.info("Data loaded: ", response)
          this.equipos = response.data
      })
      .catch(error => {
          console.error(error)
          this.error = "Failed to load equipos"
      })
      .finally(() => this.loading = false)
    },

    created () {
      let data = localStorage.getItem('todos')

      if (data != null) {
        this.tasks = JSON.parse(data)
      }

      this.getEquipos()
    }
  },
  mounted () {
    this.created()
  }
}
</script>
