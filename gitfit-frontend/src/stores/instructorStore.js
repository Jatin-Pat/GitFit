import {defineStore} from 'pinia';
import { createInstructor } from '@/api';

export const useInstructorStore = defineStore({
    id: 'instructor',
    state: () => ({
        customer: JSON.parse(localStorage.getItem('instructor')) || null,
    }),
    actions: {
        async createInstructor(instructor) {
            try {
                console.log('Creating instructor', instructor);
                const response = await createInstructor(instructor);
                console.log("no error in creating instructor");
                localStorage.setItem('instructor', JSON.stringify(response.data));
                this.updateInstructorFromLocalStorage();
                // this.instructor = response.data;
                console.log(this.instructor);
                return response;
            } catch (error) {
                console.log(error);
                console.log("error store");
                return error.response;
            }
        },
        async updateInstructorPassword(username, password) {
            try {
                console.log('Updating instructor password');
                const response = await updateInstructorPassword(username, password);
                console.log(response)
                localStorage.setItem('instructor', JSON.stringify(response.data));
                this.updateInstructorFromLocalStorage();
                return response;
            } catch (error) {
                return error.response;
            }
        },
        updateInstructorFromLocalStorage() {
            this.instructor = JSON.parse(localStorage.getItem('instructor'))||null;
        },
        async fetchInstructors() {
            try {
              const response = await fetchInstructors();
              this.instructors = response.data;
            } catch (error) {
              console.error(error);
            }
          },
          async deleteInstructor(username) {
            try {
              await deleteInstructor(username);
              this.fetchInstructors();
            } catch (error) {
              console.error(error);
            }
          },
    },
});